const functions = require('firebase-functions');
const express = require("express");
const cors = require("cors");

const app = express();

app.use(cors());
app.get('/start', (req, res) => res.send("starting movie night"));
app.get('/stop', (req, res) => res.send("stopping movie night"));

exports.movieMode = functions.https.onRequest(app);
