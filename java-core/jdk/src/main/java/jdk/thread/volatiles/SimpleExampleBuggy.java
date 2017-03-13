package jdk.thread.volatiles;/*
   Copyright 2013 Christian Kumpe

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

/**
 * The original code containing the bug.
 */
class SimpleExampleBuggy {
    static class Looper extends Thread {
        boolean finish = false;

        @Override public void run() {
            while (!finish) {
                // do something
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Looper looper = new Looper();
        looper.start();
        Thread.sleep(1000); // wait 1s
        looper.finish = true;
        System.out.println("Wait for Looper to terminate...");
        looper.join();
        System.out.println("Done.");
    }
}
