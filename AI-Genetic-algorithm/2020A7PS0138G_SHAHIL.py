import random
import time

import matplotlib.pyplot as plt 
from numpy import insert
from Graph_Creator import *

BestFitness = 0
BestStates = []
population =[]
it = 0
Fitnessplot =[]
Genplot = []


def GeneticAlgorithmFn(edges):

    clr  = ['R', 'G', 'B']
    global BestFitness, population, BestStates, Fitnessplot

    parents=[]
    fitnessValues = []

    done = []
    for x in range(len(population)):
        done.clear()
        for y in range(50):
            done.append(0)
        sum = 50 
        for i in edges:                       
            if population[x][i[0]] == population[x][i[1]]:
                if done[i[0]] == 0 :
                    sum-=1
                    done[i[0]] = 1
                if done[i[1]] == 0 :
                    sum-=1   
                    done[i[1]] = 1             
        fitnessValues.append(sum)

    temp2 = []
    for i in range (len(fitnessValues)):
        for j in range(i + 1, len(fitnessValues)):
            if(fitnessValues[i] < fitnessValues[j]):
                temp = fitnessValues[i]
                fitnessValues[i] = fitnessValues[j]
                fitnessValues[j] = temp
                temp2 = population[i]
                population[i] = population[j]
                population[j] = temp2
                

    if(fitnessValues[0] > BestFitness):
        BestFitness = fitnessValues[0]
        BestStates = population[0]
    
    # Taking top 2 as parents 
    parents = population[0:2] 
    
    # single point crossover 

    # choose = random.randint(0, 49)
    # parents = parents + [(parents[0][0:choose +1] +parents[1][choose+1:50])] 
    # parents = parents + [(parents[1][0:choose +1] +parents[0][choose+1:50])]


    # Two point crossover

    choose = random.randint(1,48) 
    choose2 = random.randint(0, choose)
    choose3 = random.randint(choose + 1, 49)   
    parents = parents + [(parents[0][0:choose2 + 1] + parents[1][choose2 +1 : choose3 + 1] + parents[0][choose3 + 1 : 50])]
    parents = parents + [(parents[1][0:choose2 + 1] + parents[0][choose2 +1 : choose3 + 1] + parents[1][choose3 + 1 : 50])]


 # This is linear crossover

    # child1 = parents[0]
    # child2 = parents[1]
    # p = 0.5                      #1/50 since 50 is the number of edges
    # for n in range(50):
       
    #     if(random.uniform(0,1) < p):
    #         temp = child1[n]
    #         child1[n] = child2[n]
    #         child2[n] = temp
    
    # parents = parents + [child1]
    # parents = parents + [child2]

#    Mutating the 2 child states  

    # probablility = random.randint(0,BestFitness)        
    # if probablility == 0 :
    #     for j in range(15):
    #         x = 2
    #         y = random.randint(0,49)
    #         random_num = random.choice(clr)
    #         parents[x][y] = str(random_num)
    #         x = 3
    #         y = random.randint(0,49)
    #         random_num = random.choice(clr)
    #         parents[x][y] = str(random_num)
    # population = parents


# Mutating parents along with child with relatively high probabilty

    probablility = random.randint(0,5) #Random probability
    if probablility == 0 :
        for j in range(int(len(edges) / 20)): #random mutations 
            x = random.randint(0,3)
            y = random.randint(0,49)
            random_num = random.choice(clr)
            parents[x][y] = str(random_num)
    population = parents


def funct(edges):
    global population, BestFitness, BestStates, Fitnessplot, Genplot

    clr  = ['R', 'G', 'B']
    for i in range(1000):
        RandomColour = random.choices(clr, k = 50)
        population.append(RandomColour)

    t_start = time.time()

    global it 
    it = 0
    while(  time.time() < t_start + 45 and BestFitness < 50):
        GeneticAlgorithmFn(edges)        
        Fitnessplot.append(BestFitness)
        Genplot.append(it + 1)
        it += 1
        
    ttaken = (time.time() - t_start)

  
    print('Roll no. : 2020A7PS0138G')
    print('Number of edges :', len(edges))
    print('Best states:')
    
    if not BestStates:
        print("None")
    else:
        for i in range(50):
            print(i,":",BestStates[i], end =", "),
            # print()
    print()
    print('Fitness value of best state :',BestFitness)
    print('Time taken :' ,ttaken, 'seconds')
    # print("Generation", it)
    
        
def main():
    gc = Graph_Creator()

#    ********* You can use the following two functions in your program

    # edges = gc.CreateGraphWithRandomEdges(100) # Creates a random graph with 50 vertices and 200 edges
    # print(len(edges))
    # print()
    edges = gc.ReadGraphfromCSVfile("100") # Reads the edges of the graph from a given CSV file
    # print(len(edges))
    # print()
    
    funct(edges)

    # Use the below code to plot fitness value vs generation
    # plt.plot(Genplot, Fitnessplot)
    # plt.plot(Genplot, Fitnessplot, color='green', linestyle='dashed', linewidth = 1,
    #      marker='o', markersize=3)
    # plt.xlabel("generation")
    # plt.ylabel("best-fitness")
    # plt.show()
#    **********
#    Write your code for find the optimum state for the edges in test_case.csv file using Genetic algorithm
    

if __name__=='__main__':    
    main()










           
    