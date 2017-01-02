var path = require('path')
var webpack = require('webpack')
var HtmlWebpackPlugin = require('html-webpack-plugin')
var ExtractTextPlugin = require('extract-text-webpack-plugin')
var autoprefixer = require('autoprefixer')


module.exports = function(dev) {
    var config = {
        devtool: dev ? 'cheap-module-eval-source-map' : 'hidden-source-map',
        debug: dev,
        entry: {
            app: './src/app/root'
        },
        devServer:{
            stats: {
                chunks: false,
                colors: true
            },
            contentBase: 'src/www/',  //Relative directory for base of server
            hot: true,
            inline: true,
            port: 8100        //Port Number
        },
        output: {
            path: path.join(__dirname, 'dist'),
            filename: '[name].bundle.js'
        },
        plugins: [
            new webpack.optimize.OccurenceOrderPlugin(),
            new HtmlWebpackPlugin({
                template: './src/www/index.html',
                inject: 'body'
            }),
            new ExtractTextPlugin('[name].bundle.css')
        ],
        module: {
            loaders: [{
                test: /\.js$/,
                loader: 'babel',
                exclude: /node_modules/
            }, {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract('style', 'css?sourceMap!postcss')
            }, {
                test: /\.(png|jpg|jpeg|gif|svg|woff|woff2|ttf|eot)$/,
                loader: 'file'
            }, {
                test: /\.html$/,
                loader: 'raw'
            }]
        },
        postcss: [
            autoprefixer({
                browsers: ['last 2 version']
            })
        ]
    }
    if (!dev) {
        config.plugins.concat(
            new webpack.DefinePlugin({
                "process.env": {
                    // This has effect on the react lib size
                    "NODE_ENV": JSON.stringify("production")
                }
            }),
            new webpack.optimize.DedupePlugin(),
            new webpack.optimize.UglifyJsPlugin({
                compress: {
                    warnings: false
                }
            })
        )
    }

    return config
};
