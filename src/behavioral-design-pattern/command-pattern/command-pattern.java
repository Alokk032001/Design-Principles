import java.util.Stack;

class Light {
    public void on() {
        System.out.println("Light turned ON");
    }

    public void off() {
        System.out.println("Light turned OFF");
    }
}

class AC {
    public void on() {
        System.out.println("AC turned ON");
    }

    public void off() {
        System.out.println("AC turned OFF");
    }
}

interface Command {
    void execute();

    void undo();

    void redo();
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }

    @Override
    public void redo() {
        execute();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }

    @Override
    public void redo() {
        execute();
    }
}

class ACOnCommand implements Command {
    private AC ac;

    public ACOnCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.on();
    }

    @Override
    public void undo() {
        ac.off();
    }

    @Override
    public void redo() {
        execute();
    }
}

class ACOffCommand implements Command {
    private AC ac;

    public ACOffCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.off();
    }

    @Override
    public void undo() {
        ac.on();
    }

    @Override
    public void redo() {
        execute();
    }
}

class RemoteControl {
    private Command[] buttons = new Command[4];
    private Stack<Command> commandHistory = new Stack<>();
    
    public void setCommand(int buttonIndex, Command command) {
        this.buttons[buttonIndex] = command;
    }

    public void pressButton(int buttonIndex) {
        Command command = this.buttons[buttonIndex];
        if (command != null) {
            command.execute();
            commandHistory.push(command);
        }
    }

    public void pressUndo() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            command.undo();
        }
    }

    public void pressRedo() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.peek();
            command.redo();
        }
    }
}

class Main {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        AC bedroomAC = new AC();

        RemoteControl remote = new RemoteControl();
        remote.setCommand(0, new LightOnCommand(livingRoomLight));
        remote.setCommand(1, new LightOffCommand(livingRoomLight));
        remote.setCommand(2, new ACOnCommand(bedroomAC));
        remote.setCommand(3, new ACOffCommand(bedroomAC));

        remote.pressButton(0); // Turn on light
        remote.pressButton(2); // Turn on AC

        remote.pressUndo(); // Undo AC on
        remote.pressUndo(); // Undo light on

        remote.pressRedo(); // Redo light on
        remote.pressRedo(); // Redo AC on
    }
}