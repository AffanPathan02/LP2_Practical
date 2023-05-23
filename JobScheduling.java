import java.util.*;
class Job{
    public String name;
    public int deadline;
    public int profit;

    public Job(String name,int deadline,int profit){
        this.name=name;
        this.deadline=deadline;
        this.profit=profit;
    }

}

public class JobScheduling{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter the number of Jobs: ");
        int numJobs=sc.nextInt();

        List<Job> jobs=new ArrayList<>();

        for(int i=0;i<numJobs;i++){
            System.out.println("Enter the detail for Job"+(i+1)+":");

            System.out.println("Name:");
            String name=sc.next();

            System.out.println("Deadline:");
            int deadline=sc.nextInt();

            System.out.println("Profit:");
            int profit=sc.nextInt();

            Job job=new Job(name,deadline,profit);
            jobs.add(job);
        }

        Collections.sort(jobs, (job1, job2) -> Integer.compare(job2.profit, job1.profit));

        
        int maxDeadline=0;

        for(Job job:jobs){
            if(job.deadline>maxDeadline){
                maxDeadline=job.deadline;
            }
        }

        int result[]=new int[maxDeadline];
        Arrays.fill(result,-1);

        int totalProfit=0;

        for(Job job:jobs){
            int deadline=job.deadline;

            for(int i=deadline-1;i>=0;i--){
                if(result[i]==-1){
                    result[i]=jobs.indexOf(job);
                    totalProfit+=job.profit;
                    break;
                }
            }
        }

        for(int i=0;i<maxDeadline;i++){
            if(result[i]!=-1){
                Job job=jobs.get(result[i]);
                System.out.println("Job: " + job.name+ ", Deadline: " + job.deadline+ ", Profit: " + job.profit);
            }
        }
        System.out.println("Total Profit: " + totalProfit);
    }
}