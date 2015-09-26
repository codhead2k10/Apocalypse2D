#version 120


in vec3 pos;
in vec2 tc;

uniform sampler2D texSampler;

varying out vec4 colour;

void main()
{
   // float intensity = 1.0 / length(pos.xy) * 0.3;
    colour =  texture2D(texSampler, tc.xy) /** intensity*/;
}