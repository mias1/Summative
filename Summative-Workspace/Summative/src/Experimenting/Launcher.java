package Experimenting;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher {
	public static void main(String[] args) {
		SonicBlast myProgram = new SonicBlast();
		LwjglApplication launcher = new LwjglApplication(myProgram);
	}
}
