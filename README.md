# Swing Methods — README
This README explains what the Swing.java file contains and how to use it.

Overview
--------
Swing.java is a small helper class that extends `JFrame` and provides a few ready-made button panel layouts you can drop into a Swing application. It was written as a simple template for creating control panels (for example, a WASD-style control pad or a single-button dialog). Buttons have hover and pressed visual effects and call a local `handler` method when clicked.

Quick highlights
- Provides three main layouts through constructors:
  - "wasd": a 3×3 style layout with directional buttons and placeholders.
  - "one": a single-button panel (label provided).
  - default: empty panel.
- Custom button creation utilities: `makeClrButton(...)` and `makeTxtButton(...)`.
- Built-in `handler(String lbl)` routes actions for the example buttons (calls `TurtleDrawingKaleb.grab(...)` for directional controls).
- Color helper functions: `lighten(...)` and `darken(...)`.
- Small state fields: `selected` (index of currently selected turtle) and `turtles` (number of turtles, default 10).

What's in the file (quick reference)
-----------------------------------
Fields
- `private int selected = 0;` — index currently selected by left/right buttons.
- `private int turtles = 10;` — total number used to wrap `selected`.

Constructors
- `Swing(String tlt, String type, String btn)` — main constructor. `tlt` sets the window title. `type` chooses which layout to create: `"wasd"`, `"one"`, or anything else (empty panel). `btn` is the label used for the "one" layout.
- `Swing(String tlt, String type)` — delegates to main constructor (no `btn` label).
- `Swing(String tlt)` — window with title only.
- `Swing()` — no-args default.

Layout factories
- `private JPanel wasd()` — builds the WASD-style control panel. Adds directional text buttons using `makeTxtButton(...)` and three placeholder colored buttons (created by `makeClrButton()`).
- `private JPanel oneButton(String tlt)` — builds a panel containing a single text button.

Button factories
- `public JButton makeClrButton(Color clr, String str)` — creates a colored button with no visible text (name set to `str`), hover/press visuals and action wired to `handler`.
- `public JButton makeClrButton(String str)` — same as above with default color (RGB 25,90,220).
- `public JButton makeClrButton()` — default colored button and default name based on the color string.
- `public JButton makeTxtButton(String txt, Color clr)` — creates a text button styled with the given color, hover/press visuals and action wired to `handler`.
- `public JButton makeTxtButton(String txt)` — same as above using default color (RGB 25,90,220).

Action handling
- `private void handler(String lbl)` — central switch that handles button press logic. Notable cases:
  - `"Color 25 90 220"` — calls `System.exit(0)` (this is the default name for blank colored buttons created by `makeClrButton()`).
  - `"l"` and `"r"` — decrement/increment `selected` (with wrap-around) and print the new value.
  - `"up"`, `"left"`, `"down"`, `"right"`, `"star"` — call `TurtleDrawingKaleb.grab(selected, <actionCode>)` with codes 1–5. This integrates with `TurtleDrawingKaleb` in your project.
  - default — prints that the function for the clicked label isn't found.

Color helpers (visual effects)
- `public Color lighten(Color color, double factor)` — returns a lightened color.
- `public Color darken(Color color, double factor)` — returns a darkened color.

How to use it
--------------
Basic usage examples (drop into your `main` or another UI initializer):

```java
// Open the WASD control window
new Swing("Turtle Controls", "wasd");

// Open a single-button window
new Swing("Action", "one", "Click Me");

// Open a titled window with default/empty content
new Swing("Empty Window");
```

Notes and gotchas (important)
-----------------------------
1. String comparison bug:
   - The class uses `type == "wasd"` and `type == "one"` to decide the layout. In Java, string equality should be checked with `.equals(...)` (or `Objects.equals(...)`). Using `==` compares references and may fail unexpectedly. Recommended replacement:
     - `"wasd".equals(type)` or `type != null && type.equals("wasd")`.
2. Visibility and layout order:
   - `setVisible(true)` is called in `defaultValues(...)` before the panel is added. Best practice is to configure the frame (add content, call `pack()` or `setSize(...)`) and then call `setVisible(true)` last.
3. Exit button:
   - The default colored placeholder button has a name like `"Color 25 90 220"` and the handler calls `System.exit(0)` for that name. If you don't want the window to close on those blanks, either change the handler or pass explicit names when creating `makeClrButton(...)`.
4. Tight coupling:
   - `handler(...)` calls `TurtleDrawingKaleb.grab(...)` for some labels. That couples this utility to your turtle project. If reusing this Swing template elsewhere, either:
     - Modify `handler(...)` to call other code, or
     - Add a listener registration mechanism to delegate handling to another class.
5. UI sizing:
   - The code uses `setSize(...)` inside layout methods. If you prefer automatic sizing, consider using `pack()` instead and set preferred sizes for components.

Suggested small improvements
----------------------------
- Fix string comparisons to `.equals(...)`.
- Move `setVisible(true)` to the end after components are added.
- Replace hard-coded `turtles` field with a setter or a constructor parameter.
- Provide a way to register action callbacks (listeners) for buttons instead of the single `handler` method.
- Optionally add `pack()` and `setLocationRelativeTo(null)` (already used) before showing.

Extending or integrating
------------------------
- To integrate with your turtle code, keep the `TurtleDrawingKaleb.grab(selected, code)` calls or replace them with your own API.
- To change default color, modify the default color instantiation in `makeClrButton(...)` and `makeTxtButton(...)`.
- To add more actions, add more cases in `handler(...)` (or better: implement a listener map keyed by button name).

License / Author
----------------
The file is authored by Kaleb (as indicated in the source header). Use and modify as needed for your project or classwork.

If you want, I can:
- Create a small patch to fix the `==` vs `.equals` bug and move `setVisible(true)` to the end.
- Add an event listener registration API so external code can attach custom handlers to button names.