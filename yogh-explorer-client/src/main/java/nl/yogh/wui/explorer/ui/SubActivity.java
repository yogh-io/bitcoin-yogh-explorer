package nl.yogh.wui.explorer.ui;

public interface SubActivity {
  default void onStart() {
    // Default to no-op
  }
  
  default void onStop() {
    
  }
}
