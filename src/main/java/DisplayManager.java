public class DisplayManager {
    private static final String DISPLAY_LOGO = "    ____        _        \n"
                                     + "   |  _ \\ _   _| | _____ \n"
                                     + "   | | | | | | | |/ / _ \\\n"
                                     + "   | |_| | |_| |   <  __/\n"
                                     + "   |____/ \\__,_|_|\\_\\___|";
    private static final String DISPLAY_GREET_START = "     ____________________________________________________________\n" +
            "        Hello! I'm Duke\n" +
            "        What can I do for you?\n" +
            "    ____________________________________________________________";
    private static final String DISPLAY_GREET_END = "    ____________________________________________________________\n" +
            "        Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________";
    private static final String DISPLAY_HORIZONTAL_SEPARATOR = "    ____________________________________________________________";
    public static final String DISPLAY_MESSAGE_INDENT = "        ";
    public static final String DISPLAY_TASK_INDENT = "    ";

    public void printStartGreet() {
        System.out.println(DISPLAY_LOGO);
        System.out.println(DISPLAY_GREET_START);
    }

    public void printEndGreet() {
        System.out.println(DISPLAY_GREET_END);
    }

    public static void printHorizontalSeparator() {
        System.out.println(DISPLAY_HORIZONTAL_SEPARATOR);
    }

    public static String createBox(String content) {
        return "[" + content + "]";
    }

    public static String createListBox(String taskType, String taskStatus) {
        return createBox(taskType) + createBox(taskStatus);
    }

    public static void printCreateTask(Task task) {
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "Got it. I've added this task:");
        System.out.println(DISPLAY_MESSAGE_INDENT + DISPLAY_TASK_INDENT + task);
        System.out.println(DISPLAY_MESSAGE_INDENT + "Now you have " + (TaskManager.getTaskCount() + 1) + " tasks in the list.");
        printHorizontalSeparator();
    }

    public static void printMultipleTasks(Task[] tasks) {
        int counter = 0;
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(DISPLAY_MESSAGE_INDENT + (counter + 1) + ". " + task);
            counter++;
        }
        printHorizontalSeparator();
    }

    public static void printMultipleValidSetDone(Task[] tasks, int[] validIndexes) {
        System.out.println(DisplayManager.DISPLAY_MESSAGE_INDENT + "Nice! I've marked these tasks as done:");
        for (int validIndex : validIndexes) {
            System.out.println(DISPLAY_MESSAGE_INDENT + DISPLAY_TASK_INDENT + tasks[validIndex - 1]);
        }
    }

    public static void printMultipleAlreadyDone(int[] doneIndexes) {
        System.out.print("\n");
        for (int doneIndex : doneIndexes) {
            System.out.println(DISPLAY_MESSAGE_INDENT + "Ignoring entry " + doneIndex + " as it has been done before.");
        }
    }

    public static void printMultipleOutOfRange(int[] outOfRangeIndexes) {
        System.out.print("\n");
        for (int outOfRangeIndex : outOfRangeIndexes) {
            System.out.println(DISPLAY_MESSAGE_INDENT + "Entry " + outOfRangeIndex + " does not exist.");
        }
    }

    public static void printSetAsDoneResult(Task[] tasks, int[] outOfRangeIndexes, int[] validIndexes, int[] doneIndexes) {
        int outOfRangeCount, validIndexCount, doneIndexCount;

        if (outOfRangeIndexes == null) {
            outOfRangeCount = 0;
        } else {
            outOfRangeCount = outOfRangeIndexes.length;
        }
        if (validIndexes == null) {
            validIndexCount = 0;
        } else {
            validIndexCount = validIndexes.length;
        }
        if (doneIndexes == null) {
            doneIndexCount = 0;
        } else {
            doneIndexCount = doneIndexes.length;
        }

        if (outOfRangeCount + validIndexCount + doneIndexCount == 0 && TaskManager.hasInvalidIndex) {
            DisplayManager.printHorizontalSeparator();
            TaskManager.hasInvalidIndex = false;
            return;
        } else if (outOfRangeCount + validIndexCount + doneIndexCount == 0) {
            return;
        }

        if (!TaskManager.hasInvalidIndex) {
            DisplayManager.printHorizontalSeparator();
        } else {
            TaskManager.hasInvalidIndex = false;
            System.out.print("\n");
        }

        if (validIndexCount != 0) {
            DisplayManager.printMultipleValidSetDone(tasks, validIndexes);
        }
        if (doneIndexCount != 0) {
            DisplayManager.printMultipleAlreadyDone(doneIndexes);
        }
        if (outOfRangeCount != 0) {
            DisplayManager.printMultipleOutOfRange(outOfRangeIndexes);
        }

        DisplayManager.printHorizontalSeparator();
    }

    public static void printErrorList() {
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "No tasks found in the list.");
        printHorizontalSeparator();
    }

    public static void printErrorIndex(String[] invalidIndexes, int invalidCount) {
        DisplayManager.printHorizontalSeparator();
        for (int i = 0; i < invalidCount; i++) {
            System.out.println(DISPLAY_MESSAGE_INDENT + invalidIndexes[i] + " is not a valid index.");
        }
    }
}
