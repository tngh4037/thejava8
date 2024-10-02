package com.study.java8.section6;

// [ 자바 Concurrent 프로그래밍 소개 ]
//
// Concurrent 소프트웨어 ?
// : 동시에 여러 작업을 할 수 있는 소프트웨어
// : 예) 웹 브라우저로 유튜브를 보면서 키보드로 문서에 타이핑을 할 수 있다.
// : 예) 녹화를 하면서 인텔리J로 코딩을 하고 워드에 적어둔 문서를 보거나 수정할 수 있다.
//
// 자바에서 지원하는 Concurrent 프로그래밍
// - 멀티프로세싱 ( ProcessBuilder )
// - 멀티쓰레드 ( Thread / Runnable )
//
public class ConcurrentIntro {

    public static void main(String[] args) throws InterruptedException {
        // 스레드 생성 방법1) Thread 상속
        MyThread myThread1 = new MyThread();
        myThread1.start();

        // 스레드 생성 방법2) Runnable 구현
        Thread myThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread: " + Thread.currentThread().getName());
            }
        });
        myThread2.start();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}

// [ 쓰레드 주요 기능 ]
// 1) 현재 쓰레드 멈춰두기 (sleep): 다른 쓰레드가 처리할 수 있도록 기회를 주지만 그렇다고 락을 놔주진 않는다. (잘못하면 데드락 걸릴 수 있다.)
// 2) 다른 쓰레드 깨우기 (interrupt): 다른 쓰레드를 깨워서 interruptedExeption을 발생시킨다. ( 그 에러가 발생했을 때 할 일은 코딩하기 나름. 종료 시킬 수도 있고 계속 하던 일 할 수도 있고. )
// 3) 다른 쓰레드 기다리기 (join): 다른 쓰레드가 끝날 때까지 기다린다. 현재 실행 중인 스레드가 다른 스레드의 작업이 완료될 때까지 대기 ( ex. 대기할스레드.join(); )


// 개발자가 스레드를 직접 코딩으로 제어하고 관리하는 것 -> 매우 복잡하다. (그렇게 하면 안됨.) -> 그래서 Executor 라는 게 나온다. ( Executor 사용법을 익히면 Future 를 사용할 수 있게 된다. ) -> Future 를 사용할 수 있게 되면, CompletableFuture 를 알수있다.
