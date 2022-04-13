const dbs = require("../dbs/dbs.js")
const bcrypt = require("bcryptjs")
const expressJwt = require("express-jwt")
const jwt = require("jsonwebtoken")

const adminController = {
    login: function(req, res) {
        var resolve = {}, sql
        if(Number.parseInt(req.body.player[0])) {
            sql = `SELECT * FROM player_info WHERE player_xuid = '${req.body.player}'`
        } else {
            sql = `SELECT * FROM player_info WHERE player_name = '${req.body.player}'`
        }
        dbs.get(sql, (err, row) => {
            if(err) {
                resolve.status = 0
                resolve.message = err.message
            }
            if(!row) {
                resolve.status = 0
                resolve.message = `Player/Xuid "${req.body.player}" is not in the record.`
                return res.send(resolve)
            }
            if(bcrypt.compareSync(req.body.password, row.player_auth)) {
                resolve.status = 1
                resolve.message = "Authentication success."
            } else {
                resolve.status = 0
                resolve.message = "Authentication failed."
            }
            res.send(resolve)
        })
    },
    register: function(req, res) {
        var resolve = {}, sql, sql2
        if(req.body.password !== req.body.password2) throw res.returnError("Different password.")
        if(Number.parseInt(req.body.player[0])) {
            sql = `SELECT * FROM player_info WHERE player_xuid = '${req.body.player}'`
            var enPwd = bcrypt.hashSync(req.body.password)
            sql2 = `INSERT INTO player_info (player_xuid, player_auth) VALUES ('${req.body.player}', '${enPwd}')`
        } else {
            sql = `SELECT * FROM player_info WHERE player_name = '${req.body.player}'`
            var enPwd = bcrypt.hashSync(req.body.password)
            sql2 = `INSERT INTO player_info (player_name, player_auth) VALUES ('${req.body.player}', '${enPwd}')`
        }
        dbs.get(sql, (err, row) => {
            if(err) throw res.returnError(err)
            if(row) throw res.returnError(`Player/Xuid "${req.body.player}" is already in the record.`)
        })
        dbs.run (sql2, (err) => {
            if(err) throw res.returnError(err)
            else {
                resolve.status = 1
                resolve.message = "Register success."
                return res.send(resolve)
            }
        })
    }
}

module.exports = adminController
