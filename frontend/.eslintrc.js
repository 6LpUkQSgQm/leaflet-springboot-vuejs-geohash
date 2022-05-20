module.exports = {
    "rules": {
        "node/no-missing-import": ["error", {
            "allowModules": ["vue"],
            "resolvePaths": ["../frontend/node_modules/vue"],
            "tryExtensions": [".js", ".json", ".node"]
        }]
    }
};
