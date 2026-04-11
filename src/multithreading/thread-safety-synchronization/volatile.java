class SharedData {
    private volatile boolean isEnabled = false;

    public void enable()  { isEnabled = true;  } // written to RAM immediately
    public boolean check() { return isEnabled; }  // read from RAM always
}
