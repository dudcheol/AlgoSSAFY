package algoSSAFY.week31;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 차량_정비소 {

    private static int N,M,K,A,B;

    private static class Customer{
        int num;
        int t; // 정비소 방문 시간
        int receptionIn;
        int qIntime;
        int reception; // 사용한 접수처
        int repair; // 사용한 정비처

        public Customer(int num, int t) {
            this.num = num;
            this.t = t; // 도착시간
        }
    }

    private static class Desk{
        int num;
        int time; // 걸리는 시간
        Customer customer; // 이용중인 고객

        public Desk(int num, int time, Customer customer) {
            this.num = num;
            this.time = time;
            this.customer = customer;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            K=Integer.parseInt(st.nextToken());
            A=Integer.parseInt(st.nextToken());
            B=Integer.parseInt(st.nextToken());
            Desk[] receptionDesk = new Desk[N];
            Desk[] repairDesk = new Desk[M];
            st = new StringTokenizer(br.readLine()," ");
            for (int i = 0; i < N; i++) {
                receptionDesk[i] = new Desk(i+1, Integer.parseInt(st.nextToken()), null);
            }
            st = new StringTokenizer(br.readLine()," ");
            for (int i = 0; i < M; i++) {
                repairDesk[i] = new Desk(i+1, Integer.parseInt(st.nextToken()), null);
            }
            st = new StringTokenizer(br.readLine()," ");
            PriorityQueue<Customer> arrivalTime = new PriorityQueue<>((o1,o2)->{
                return Integer.compare(o1.t, o2.t);
            });
            for (int i = 0; i < K; i++) {
                arrivalTime.offer(new Customer(i+1, Integer.parseInt(st.nextToken())));
            }

            // 대기열 생성
            PriorityQueue<Customer> receptionWait = new PriorityQueue<>((o1,o2)->{
                return Integer.compare(o1.num,o2.num);
            });
            PriorityQueue<Customer> repairWait = new PriorityQueue<>((o1,o2)->{
                int ret = Integer.compare(o1.qIntime, o2.qIntime);
                if (ret==0) ret = Integer.compare(o1.reception, o2.reception);
                return ret;
            });

            int time=0;
            int sum=0;
            int finish=0;
            while (finish<K){
                // 도착시간 확인하여 접수 대기열로 이동
                while (!arrivalTime.isEmpty() && arrivalTime.peek().t==time){
                    Customer c = arrivalTime.poll();

                    // 접수데스크 대기열에 넣기
                    receptionWait.offer(c);
                }

                // 접수데스크 뺄 사람 빼고 정비대기열에 넣기
                for (int i = 0; i < N; i++) {
                    if(receptionDesk[i].customer!=null && time-receptionDesk[i].customer.receptionIn>=receptionDesk[i].time){
                        receptionDesk[i].customer.qIntime = time; // 접수 시 동시에 접수할 수 있으므로 접수시간 기록
                        repairWait.offer(receptionDesk[i].customer);
                        receptionDesk[i].customer=null;
                    }
                }

                // 접수 기다리고 있는 사람 접수데스크 넣기
                for (int i = 0; i < N; i++) {
                    if (receptionDesk[i].customer==null){
                        if(!receptionWait.isEmpty()) {
                            receptionDesk[i].customer = receptionWait.poll();
                            receptionDesk[i].customer.receptionIn = time;
                            receptionDesk[i].customer.reception = i+1;
                        }
                    }
                }

                // 정비데스크 뺄 사람 빼기
                for (int i = 0; i < M; i++) {
                    if(repairDesk[i].customer!=null && time-repairDesk[i].customer.qIntime>=repairDesk[i].time){
                        if (repairDesk[i].customer.reception==A && repairDesk[i].customer.repair==B){
                            sum+=repairDesk[i].customer.num;
                        }
                        finish++;
                        repairDesk[i].customer=null;
                    }
                }

                // 정비 기다리고 있는 사람 정비데스크 넣기
                for (int i = 0; i < M; i++) {
                    if (repairDesk[i].customer==null){
                        if(!repairWait.isEmpty()) {
                            repairDesk[i].customer = repairWait.poll();
                            repairDesk[i].customer.qIntime = time; // 정비받기 시작한 시간 기록
                            repairDesk[i].customer.repair = i+1;
                        }
                    }
                }
                time++;
            }
            sb.append('#').append(test_case).append(' ').append(sum==0?-1:sum).append('\n');
        }
        System.out.print(sb);
    }
}

/*
6번
1
3 2 10 1 2
5 5 8
3 5
0 0 4 7 8 8 9 9 10 10

답 15
 */