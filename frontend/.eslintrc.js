// .eslintrc.js
module.exports = {
    "settings": {
        "node": {
            "allowModules": ["vue"],
            "resolvePaths": [__dirname],
            "tryExtensions": [".js", ".json", ".node"]
        }
    },
    "rules": {
        "node/no-missing-import": "error"
    }
}