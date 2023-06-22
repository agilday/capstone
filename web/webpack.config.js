const path = require('path');
const CopyPlugin = require("copy-webpack-plugin");
const Dotenv = require('dotenv-webpack');

// Get the name of the appropriate environment variable (`.env`) file for this build/run of the app
const dotenvFile = process.env.API_LOCATION ? `.env.${process.env.API_LOCATION}` : '.env';

module.exports = {
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: "static_assets", to: "../",
          globOptions: {
            ignore: ["**/.DS_Store"],
          },
        },
      ],
    }),
    new Dotenv({ path: dotenvFile }),
  ],
  optimization: {
    usedExports: true
  },
  entry: {
    createAppointment: path.resolve(__dirname, 'src', 'pages', 'CreateAppointment.js'),
    createClientProfile: path.resolve(__dirname, 'src', 'pages', 'CreateClientProfile.js'),
    createService: path.resolve(__dirname, 'src', 'pages', 'CreateService.js'),
    allAppointments: path.resolve(__dirname, 'src', 'pages', 'AllAppointments.js'),
    viewAllClientProfiles: path.resolve(__dirname, 'src', 'pages', 'ViewAllClientProfiles.js'),
    viewClientProfile: path.resolve(__dirname, 'src', 'pages', 'ViewClientProfile.js'),
    getServiceMenu: path.resolve(__dirname, 'src', 'pages', 'GetServiceMenu.js'),
    landingPage: path.resolve(__dirname, 'src', 'pages', 'LandingPage.js'),
    test: path.resolve(__dirname, 'src', 'pages', 'test.js')
  },
  output: {
    path: path.resolve(__dirname, 'build', 'assets'),
    filename: '[name].js',
    publicPath: '/assets/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 8000,
    client: {
      // overlay shows a full-screen overlay in the browser when there are js compiler errors or warnings
      overlay: true,
    },
  }
}
