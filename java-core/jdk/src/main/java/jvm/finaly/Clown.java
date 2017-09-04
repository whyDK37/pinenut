package jvm.finaly;

class Clown {

  static int hopAround() {
    int i = 0;
    while (true) {
      try {
        try {
          i = 1;
        } finally {  // The first finaly clause
          i = 2;
        }
        i = 3;
        // This return never completes, because of
        // the continue in the second finaly clause
        return i;
      } finally {      // The second finaly clause
        if (i == 3) {
          // This continue overrides the return statement
          continue;
        }
      }
    }
  }
}
