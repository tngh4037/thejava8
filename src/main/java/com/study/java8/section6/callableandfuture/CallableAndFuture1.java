package com.study.java8.section6.callableandfuture;

import java.util.concurrent.*;

public class CallableAndFuture1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // [ Callable ]
        Callable<String> hello = new Callable<String>() { // 리턴받을 타입을 제네릭으로 정의
            @Override
            public String call() throws Exception {
                Thread.sleep(5000L);
                return "Hello";
            }
        };

        // [ Future ]
        Future<String> helloFuture = executorService.submit(hello); // Callable 이 리턴타는 타입의 Future 를 받을 수 있다.
        System.out.println("isDone: " + helloFuture.isDone()); // 작업 상태 확인 ( 작업이 완료됐으면 true 아니면 false를 리턴. )
        System.out.println("Started");

        helloFuture.cancel(true); // 작업 취소 ( parameter로 true를 전달하면 현재 진행중인 쓰레드를 interrupt하고 그러지 않으면 현재 진행중인 작업이 끝날때까지 기다린다. 참고로 false로 해서 기다렸다한들 get()으로 값을 가져올 수 없다. get()해서 가져오면 오류(CancellationException)가 발생한다. ) | 참고) 취소했으면 true 못했으면 false를 리턴한다.

     //   helloFuture.get(); // get(); 으로 값을 꺼낼 수 있다. 참고로 여기서 결과값을 가져올 때 까지 기다린다. ( 블록킹 콜 ) | 타임아웃(최대한으로 기다릴 시간)을 설정할 수 있다.

        System.out.println("isDone: " + helloFuture.isDone()); // 참고) cancel 을 해도 isDone()은 true 가 된다. 이때의 isDone()의 true는 작업이 종료됐으니 값을 가져갈 수 있다는 의미가 아니다. cancel 로 인해 종료되었다는 의미
        System.out.println("End");

        executorService.shutdown();
    }
}

// [ Callable ]
// - Runnable과 유사하지만 작업의 결과를 받을 수 있다.

// [ Future ]
// - 비동기적인 작업의 현재 상태를 조회하거나 결과를 가져올 수 있다.
