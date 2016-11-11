var ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: './src/main/webapp/app.js',
    output: {
        path: __dirname + '/src/main/webapp/public',
        filename: 'bundle.js'
    },
    module:{
        loaders:[
            {
                test: /.js$/,
                loader: 'babel',
                exclude: /node_module/,
                query: {
                    presets: ['es2015', 'react']
                }
            },
            {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract("style-loader", "css-loader")
            },
            {
                test: /\.(woff2?|ttf|eot|svg|png|jpe?g|gif)$/,
                loader: 'file'
            }
        ]
    },
    plugins:[
        new ExtractTextPlugin("styles.css")
    ]
};

// var packageJSON = require('./package.json');
// var path = require('path');
// var webpack = require('webpack');
//
// const PATHS = {
//     build: path.join(__dirname, 'target', 'classes', 'META-INF', 'resources', 'webjars', packageJSON.name, packageJSON.version)
// };
//
// module.exports = {
//     noInfo: false,
//     entry: './src/main/js/app.js',
//
//     output: {
//         path: PATHS.build,
//         publicPath: '/assets/',
//         filename: 'app-bundle.js'
//     },
//     module: {
//         loaders: [
//             {
//                 test: path.join(__dirname, '.'),
//                 exclude: /(node_modules)/,
//                 loader: 'babel-loader'
//             }
//         ]
//     }
// };
// var path = require('path');
//
// module.exports = {
//     entry: './src/main/js/app.js',
//     devtool: 'sourcemaps',
//     debug: true,
//     output: {
//         path: __dirname,
//         filename: './src/main/resources/static/built/bundle.js'
//     },
//     module: {
//         loaders: [
//             {
//                 test: path.join(__dirname, '.'),
//                 exclude: /(node_modules)/,
//                 loader: 'babel-loader'
//             }
//         ]
//     }
// };