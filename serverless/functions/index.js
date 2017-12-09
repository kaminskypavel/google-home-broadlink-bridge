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
            .push({date, command})
            .then(res);
    });
};

const app = express();
app.use(cors());

app.get('/start', (req, res) => {
    updateDatabase("start").then(() =>
        res.send("starting movie night"));
});

app.get('/stop', (req, res) => {
    updateDatabase("stop").then(() =>
        res.send("stopping movie night"));
});


exports.movieMode = functions.https.onRequest(app);
