def printJobScheduling(arr, t):
    n = len(arr)
    for i in range(n):
        for j in range(n - 1 - i):
            if arr[j][2] < arr[j + 1][2]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    result = [False] * t
    job = ['-1'] * t
    for i in range(len(arr)):
        for j in range(min(t - 1, arr[i][1] - 1), -1, -1):
            if result[j] is False:
                result[j] = True
                job[j] = arr[i][0]
                break
    print(job)


def menu():
    arr = []
    while True:
        print("Menu:")
        print("1. Add job")
        print("2. Perform job scheduling")
        print("3. Exit")
        choice = int(input("Enter your choice: "))

        if choice == 1:
            job_name = input("Enter job name: ")
            deadline = int(input("Enter deadline: "))
            profit = int(input("Enter profit: "))
            arr.append([job_name, deadline, profit])
            print("Job added successfully!")
        elif choice == 2:
            t = int(input("Enter the number of time slots available: "))
            print("Following is the maximum profit sequence of jobs:")
            printJobScheduling(arr, t)
        elif choice == 3:
            print("Exiting...")
            break
        else:
            print("Invalid choice! Try again.")


if __name__ == '__main__':
    menu()
