const express = require("express")
const cors = require("cors")
const joi = require('joi');

const playerRou = require("./router/player.js")
const adminRou = require("./router/admin.js")

const app = express()

app.use(express.urlencoded({ extended: false }))
app.use(express.json())
app.use(cors())

app.use(function(req, res, next) {
    res.returnError = function(err, status = 0) {
        res.send({
            status,
            message : err instanceof Error ? err.message : err
        })
    }
    next()
})

app.use('/api', playerRou)
app.use('/admin', adminRou)

app.use(function(err, req, res, next) {
    if(err instanceof joi.ValidationError) return res.returnError(err)
    res.returnError(err)
})

app.listen(66, () => {
    console.log("\r")
    console.log("\x1b[1m\x1b[93mExpress api server running at http://127.0.0.1:66\x1b[0m")
})
