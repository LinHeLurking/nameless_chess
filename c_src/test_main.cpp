#include <iostream>
#include "dep.h"
#include "Main.h"


using namespace std;


JNIEXPORT void JNICALL Java_Main_cpp_1main(JNIEnv *, jobject){
    Game game;
	game.game_loop();
}