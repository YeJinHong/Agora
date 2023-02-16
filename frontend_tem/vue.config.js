// vue.config.js
const fs = require("fs");
module.exports = {
  configureWebpack: {
    module: {
      rules: [
        {
          test: /\.mjs$/,
          include: /node_modules/,
          type: "javascript/auto"
        }
      ]
    }
  },
  devServer: {
    https: {
      key: fs.readFileSync('/usr/local/share/ssl/domaincomkey.pem'),
      cert: fs.readFileSync('/usr/local/share/ssl/domaincomcrt.pem'),
      ca: fs.readFileSync('/usr/local/share/ssl/rootca.pem'),
    }
  }
}
