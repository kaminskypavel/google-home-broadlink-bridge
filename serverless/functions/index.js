const functions = require('firebase-functions');
const express = require("express");
const cors = require("cors");
const moment = require("moment");
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

const updateDatabase = (command) => {
    const date = moment().format();
    return new Promise((res) => {
        const queueRef = admin.database().ref('/queue');

        queueRef
            .set({date, command})
            .then(res);
    });
};

const app = express();
app.use(cors());

app.get('/movie', (req, res) => {
    updateDatabase("movie").then(() =>
        res.send("Switching Movie Mode"));
});

app.get('/ac', (req, res) => {
    updateDatabase("ac").then(() =>
        res.send("Switching AC"));
});


exports.action = functions.https.onRequest(app);
