# Paint-Tool
Final project for Object Oriented Programming class, our own implementation of a simplified of the classic Microsoft paint written in Java using JavaFx
# Microsoft-Paint

Final project for Object Oriented Programming class, our own implementation of a simplified of the classic Microsoft paint written in Java using JavaFx.

## Goal

Because it was built for OOP class the UI wasn't the main focus, but on the principles used to create the different components, as well as the division 
between the front end and the back end. The goal being able to use the same back-end for another front-end without any kind of refactoring from the back-end.

## Key Design Choice

Probably the most interesting part of the part is that of having each front-end-figure class inherent the class from the backend, and then implement the 
abstract method draw(), such that the backend has an accessible draw method, but which ends up being defined in the front-end. To achieve this, the 
figure classes for the back-end were declared abstract

## Project Structure

```
── src
    ├── backend
    │   └── model
    │       ├── CanvasState.java
    │       ├── FigureStyle.java
    │       ├── components
    │       │   ├── Circle.java
    │       │   ├── Ellipse.java
    │       │   ├── Figure.java
    │       │   ├── Line.java
    │       │   ├── Point.java
    │       │   ├── Rectangle.java
    │       │   └── Square.java
    │       ├── exceptions
    │       │   └── BackEndException.java
    │       └── interfaces
    │           ├── Colorable.java
    │           ├── Drawable.java
    │           ├── Movable.java
    │           └── Selectable.java
    └── frontend
        ├── AppLauncher.java
        ├── AppMenuBar.java
        ├── MainFrame.java
        ├── PaintPanel.java
        ├── StatusPanel.java
        ├── components
        │   ├── CircleFrontEnd.java
        │   ├── EllipseFrontEnd.java
        │   ├── LineFrontEnd.java
        │   ├── RectangleFrontEnd.java
        │   └── SquareFrontEnd.java
        ├── controllers
        │   ├── FigureButtons.java
        │   ├── FunctionButtons.java
        │   └── MouseEvent.java
        └── engines
            ├── ButtonsEngine.java
            ├── CanvasEngine.java
            ├── ColorControlsEngine.java
            ├── HexStringEngine.java
            ├── OvalsEngine.java
            └── PolygonsEngine.java
            
```
