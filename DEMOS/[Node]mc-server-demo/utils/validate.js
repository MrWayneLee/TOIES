const joi = require("joi")

function validation(schema) {
    return function(req, res, next) {
        if(schema.validate(req.body).error) {
            throw schema.validate(req.body).error
        }
        next()
    }
}

const schema = {
    login: function() {
        var schema = joi.object({
            player: joi.string().alphanum().min(1).max(18),
            password: joi.string().min(1).max(18)
        })
        return validation(schema)
    },
    register: function() {
        var schema = joi.object({
            player: joi.string().alphanum().min(1).max(18),
            password: joi.string().min(1).max(18),
            password2: joi.string().min(1).max(18)
        })
        return validation(schema)
    }
}

module.exports = schema
