module.exports = {
    rules: {
      "node/no-unsupported-features/es-syntax": [
        "error",
        {
          version: ">=8.0.0",
          ignores: ["vue", "axios", "vuetify"],
        },
      ],
    },
  };