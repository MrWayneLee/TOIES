const sqlite3 = require("sqlite3").verbose()

var db = new sqlite3.Database("dbs/node_db.db")

const runSql = async (sql) => {
    return new Promise(async (resolve) => {
        db.run(sql, (err) => {
            resolve(err)
        })
    })
}

const query = async (sql) => {
    return new Promise(async (resolve, reject) => {
        db.all(sql, (err, rows) => {
            if(null != err) {
                reject(err)
                return
            } else {
                resolve(rows)
            }
        })
    })
}

module.exports = db
