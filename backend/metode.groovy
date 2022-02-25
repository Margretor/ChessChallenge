/*import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText '''
    {
    "piece": "pawn",
    "colour": "white",
    "position": "a2"
  }'''

assert object instanceof Map
assert object.piece.class == "pawn"
assert object.colour.class == "white"
assert object.position.class == "a2"

for (property in object) {
    output += property + ': ' + object[property]+'; ';
}
println(output);*/
import groovy.json.JsonOutput;

def json = JsonOutput.toJson([foo: 'bar', baz: [1]])

assert json == '{"foo":"bar","baz":[1]}'

def pretty = JsonOutput.prettyPrint(json)

assert pretty == '''{
    "foo": "bar",
    "baz": [
        1
    ]
}'''
for (property in pretty) {
    output += property + ': ' + pretty[property]+'; ';
}
println(output);


/*[
  {
    "piece": "pawn",
    "colour": "white",
    "position": "a2"
  },
  {
    "piece": "pawn",
    "colour": "white",
    "position": "b2"
  }*/