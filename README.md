# Filter_Camera_Live

Este repositorio contiene la implementación de una aplicación de Android que aplica un filtro negativo en tiempo real a la previsualización de la cámara del dispositivo. Utilizando técnicas de procesamiento de imágenes, la aplicación transforma los colores de la imagen en sus valores negativos en tiempo real.

## Contenido

1. [Abstract](#abstract)
2. [Tecnologías Utilizadas](#Tecnologías_Utilizadas)
3. [Implementación](#Implementación)
4. [Referencias](#Referencias)

## Abstract
Aplicar filtros en tiempo real a la previsualización de la cámara es un desafío que se puede abordar eficazmente mediante técnicas de procesamiento de imágenes. Esta aplicación de Android utiliza métodos de inversión de color para aplicar un filtro negativo a la imagen capturada por la cámara en tiempo real. La implementación incluye el uso de OpenCV para realizar las transformaciones necesarias, proporcionando una experiencia de usuario interactiva y dinámica.

## Tecnologías_Utilizadas
* Java/Kotlin: Lenguaje de programación principal para la implementación de la aplicación.
* OpenCV: Biblioteca de procesamiento de imágenes utilizada para aplicar el filtro negativo.
* Android Studio: Entorno de desarrollo integrado (IDE) para la construcción de la aplicación.

## Implementación
La implementación del proyecto se divide en varias etapas:
* Captura Cámara: Uso de la cámara en vivo del dispositivo para capturar imágenes en tiempo real.
* Preprocesamiento de Imagen: Aplicación de técnicas de procesamiento de imágenes sobre la previsualización de la cámara para preparar la imagen para el filtrado.
* Aplicación del Filtro Negativo: Uso de OpenCV para invertir los colores de la imagen, creando un efecto negativo en tiempo real.
* Visualización de Resultados: Mostrar en la pantalla del dispositivo la imagen con el filtro negativo aplicado en tiempo real.

## Referencias
OpenCV Documentation: https://docs.opencv.org/
