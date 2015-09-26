#version 120

in vec3 position;
in vec2 texCoords;

varying out vec2 tc;
varying out vec3 pos;

uniform mat4 transformation;

void main()
{
    tc = texCoords;
    pos = position;
    gl_Position = transformation * vec4(position, 1.0);
}