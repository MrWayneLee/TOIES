const dbs = require("../dbs/dbs.js")

const playerController = {
    getAllPlayer: function() {
        return dbs.query("SELECT * FROM player_info")
    },
    getPlayer: async function(player) {
        if(typeof(player) === 'string') {
            return await dbs.query(`SELECT * FROM player_info WHERE player_name = '${player}'`)
        } else if (typeof(player) === 'number') {
            return await dbs.query(`SELECT * FROM player_info WHERE player_xuid = ${player}`)
        } else {
            return 0
        }
    },
    addPlayer: async function(player) {
        if(await this.isPlayerExist(player)) {
            return player
        } else {
            return await dbs.runSql(`INSERT INTO player_info VALUES('${player.name}', ${player.xuid})`)
        }
    },
    removePlayer: async function(player) {
        return await dbs.runSql(`DELETE FROM player_info WHERE player_name = '${player}'`)
    },
    isPlayerExist: async function(player) {
        return Boolean((await dbs.query(`SELECT * FROM player_info WHERE player_xuid = ${player.xuid}`)).length)
    }
}

module.exports = playerController
