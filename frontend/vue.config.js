const path = require('path');

module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  },
  outputDir: path.resolve(__dirname, '../backend/src/main/resources/static')
};