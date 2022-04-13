const express = require("express")

const validate = require("../utils/validate.js")
const adminCon = require("../controller/adminController.js")

const router = express.Router()

router.post("/login", validate.login, adminCon.login)
router.post("/register", validate.register, adminCon.register)

module.exports = router
