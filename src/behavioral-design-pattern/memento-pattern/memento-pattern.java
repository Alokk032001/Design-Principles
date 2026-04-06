import java.util.List;
import java.util.Stack;

class ResumeEditor {
    private String name;
    private String education;
    private String experience;
    private List<String> skills;

    public void setName(String name) {
        this.name = name;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void printResume() {
        System.out.println("x:----- Resume -----");
        System.out.println("Name: " + name);
        System.out.println("Education: " + education);
        System.out.println("Experience: " + experience);
        System.out.println("Skills: " + skills);
        System.out.println("x:------------------");
    }

    public ResumeMemento save() {
        return new ResumeMemento(name, education, experience, List.copyOf(skills));
    }

    public void restore(ResumeMemento memento) {
        this.name = memento.getName();
        this.education = memento.getEducation();
        this.experience = memento.getExperience();
        this.skills = memento.getSkills();
    }

    public static class ResumeMemento {
        private final String name;
        private final String education;
        private final String experience;
        private final List<String> skills;

        public ResumeMemento(String name, String education, String experience, List<String> skills) {
            this.name = name;
            this.education = education;
            this.experience = experience;
            this.skills = skills;
        }

        public String getName() {
            return name;
        }

        public String getEducation() {
            return education;
        }

        public String getExperience() {
            return experience;
        }

        public List<String> getSkills() {
            return skills;
        }
    }
}

class ResumeHistory {
    private Stack<ResumeEditor.ResumeMemento> history = new Stack<>();

    public void save(ResumeEditor editor) {
        history.push(editor.save());
    }

    public void undo(ResumeEditor editor) {
        if (!history.isEmpty()) {
            editor.restore(history.pop());
        } else {
            System.out.println("No previous states to restore.");
        }
    }
}

class Main {
    public static void main(String[] args) {
        ResumeEditor editor = new ResumeEditor();
        ResumeHistory history = new ResumeHistory();

        editor.setName("Alice Smith");
        editor.setEducation("B.Sc. Computer Science");
        editor.setExperience("Software Engineer at TechCorp");
        editor.setSkills(List.of("Java", "Python", "SQL"));
        history.save(editor);
        editor.printResume();

        // Make some changes
        editor.setExperience("Senior Software Engineer at TechCorp");
        editor.setSkills(List.of("Java", "Python", "SQL", "Leadership"));
        history.save(editor);
        editor.printResume();

        // Undo last change
        history.undo(editor);
        editor.printResume();

        // Undo to original state
        history.undo(editor);
        editor.printResume();
    }
}
