const fs = require("fs")
const path = require("path")

const apiLogger = {
    apiLog: function(apiPath, method) {
        console.log(`\x1b[1m\x1b[93m[${new Date().toLocaleTimeString()}]\x1b[0m\x1b[32m[${apiPath}]\x1b[0m\x1b[36m[${method}]\x1b[0m`)
        var content = `[${apiPath}][${method}]`
        writeLogFile(content)
    }
}

const dbsLogger = {
    dbsLog: function(db, method) {
        
    }
}

function writeLogFile(text) {
    var content = `[${new Date().toLocaleDateString()}][${new Date().toLocaleTimeString()}]${text}\r`
    fs.appendFile(path.join(__dirname, "../", "log.txt"), content, () => {})
}

module.exports = {
    apiLogger,
    dbsLogger
}
