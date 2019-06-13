#include "online_ruin_of_future_nameless_chess_CppSync.h"
#include "dep.h"

using namespace std;

enum { success = 1, failure = -1 };

Game game;


/*
 * Class:     online_ruin_of_future_nameless_chess_CppSync
 * Method:    sync_nothing
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_online_ruin_1of_1future_nameless_1chess_CppSync_sync_1nothing(JNIEnv*, jobject) {
	return success;
}

/*
 * Class:     online_ruin_of_future_nameless_chess_CppSync
 * Method:    sync_drawboard_in_commandline
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_online_ruin_1of_1future_nameless_1chess_CppSync_sync_1drawboard_1in_1commandline(JNIEnv *, jobject){
	game.board.drawboard();
	return success;
}

