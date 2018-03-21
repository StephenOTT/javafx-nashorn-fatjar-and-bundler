load("fx:base.js");
load("fx:controls.js");
load("fx:graphics.js");

const myEs6Var = "This is a Const from ES6 features in Nashorn"

$STAGE.title = "Hello World!";
var button = new Button();
button.text = "Say 'Hello World'";
button.onAction = function() print("Hello World!");
var root = new StackPane();
root.children.add(button);
$STAGE.scene = new Scene(root, 300, 250);
$STAGE.show();