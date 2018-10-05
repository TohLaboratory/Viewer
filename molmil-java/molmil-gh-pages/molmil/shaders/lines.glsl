{
  "attributes": {
    "in_Position": -1,
    "in_Colour": -1
  },
  "uniforms": {
    "COR": -1,
    "modelViewMatrix": -1,
    "projectionMatrix": -1,
    "focus": -1,
    "fogSpan": -1,
    "uniform_color": -1
  }
}
//#vertex

uniform mat4 modelViewMatrix;
uniform mat4 projectionMatrix;
uniform vec3 COR;

attribute vec3 in_Position;

varying vec3 ex_Colour;


uniform float focus;
uniform float fogSpan;
varying float fogFactor;

#ifdef UNIFORM_COLOR
uniform vec3 uniform_color;
#else
attribute vec3 in_Colour;
#endif


void main() {
  gl_Position = vec4(modelViewMatrix * vec4(in_Position, 1.0))-vec4(COR, 0.0);
  
#ifdef ENABLE_FOG
  fogFactor = clamp((fogSpan - -gl_Position.z) / (fogSpan - focus), 0.05, 1.0);
#endif
  
  gl_Position = projectionMatrix * gl_Position;

#ifdef UNIFORM_COLOR
  ex_Colour = uniform_color;
#else
  ex_Colour = in_Colour;
#endif


}

//#fragment

precision mediump float;

varying vec3 ex_Colour;
varying float fogFactor;

const vec4 backgroundColor = vec4(0.0, 0.0, 0.0, 1.0);

void main() {
#ifdef ENABLE_FOG
  gl_FragColor = mix(backgroundColor, vec4(ex_Colour, 1.0), fogFactor);
#else
  gl_FragColor = vec4(ex_Colour, 1.0);
#endif
}
