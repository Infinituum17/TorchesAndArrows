![](https://img.shields.io/badge/modloader-fabric-brightgreen) ![](https://img.shields.io/badge/environment-client%20%2F%20server-yellow) ![](https://img.shields.io/badge/license-MIT-blue)

<a href='https://ko-fi.com/infinituum' target='_blank'><img height=35 src='https://uploads-ssl.webflow.com/5c14e387dab576fe667689cf/61e11d430afb112ea33c3aa5_Button-1-p-500.png' alt='Buy Me a Coffee at ko-fi.com' /></a>

# Torches And Arrows

A mod that makes easy placing torches in caves or big areas by using arrows to throw them.

## How to use

This mod adds a "Torch Arrow" and a "Soul Torch Arrow" which can both be used with a bow.

## For developers

If you want to add compatibility to one of your arrows simply extend the "ArrowLightSource" interface on your ArrowItem and implement the methods. If you intend to use it on a light source that can have wall placements (like a torch) you can extend the "ArrowWallLightSource" interface.
