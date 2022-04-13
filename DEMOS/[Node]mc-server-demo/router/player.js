const express = require("express")

const validate = require("../utils/validate.js")
const playerCon = require("../controller/playerController.js")

const router = express.Router()

router.get("/player", (req, res) => {
    res.send({
        status: 1,
        data: playerCon.getAllPlayer(),
        message: 'success'
    })
})

router.get("/player/:player", async (req, res) => {
    var result = await playerCon.getPlayer(req.params.player)
    var resolve = {}
    if(!result.length) {
        resolve.status = 0,
        resolve.message = 'no record of this player.'
    } else {
        resolve.status = 1,
        resolve.data = result,
        resolve.message = 'success'
    }
    res.send(resolve)
})

router.post("/player", async (req, res) => {
    var result, resolve = {}
    if(req._body) {
        result = await playerCon.addPlayer(req.body)
        if(result != null) {
            resolve.status = 0
            resolve.message = 'player is already in the record.'
        } else {
            resolve.status = 1
            resolve.message = 'success'
        }
    } else {
        resolve.status = 0
        resolve.message = 'param is empty.'
    }
    res.send(resolve)
})

router.put("/player/:player", (req, res) => {
    res.send({
        status: 0,
        message: "未开放该接口"
    })
})

router.delete("/player/:player", async (req, res) => {
    var result, resolve = {}
    result = await playerCon.removePlayer(req.params.player)
    console.log(result)
    if(result == null) {
        resolve.status = 1
        resolve.message = "success"
    } else {
        resolve.status = 0
        resolve.message = "fail"
    }
    res.send(resolve)
})

module.exports = router
