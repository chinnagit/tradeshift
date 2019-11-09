Requirements

1. Ensure java 8 is installed
2. Ensure mvn is installed
3. Ensure docker is installed.


git clone https://github.com/chinnagit/tradeshift

cd node-manager.

Application can be run in either of the below three ways.

1. 

mvn spring-boot:run

or

java -jar target\node-management.jar

or

3. using docker

a. build the image

docker image build -t nodemanagerimage:1.0 .

b. run the container

docker run -p 8080:8080 -t nodemanagerimage:1.0

access the application using url -- http://localhost:8080/node/{nodename}

sample tree is hard coded in this form

      root
      /   \
     A      B 
    /     /   \   \
   C      D    E   F
   
   

3.a. For the API --  get all descedants root node

http://localhost:8080/node/root

And observe the response as below. The data contains node information, it's height from root
and childrens of the node.

[
  {
    "name": "A",
    "height": 1,
    "childrens": [
      {
        "name": "C",
        "height": 2,
        "childrens": []
      }
    ]
  },
  {
    "name": "B",
    "height": 1,
    "childrens": [
      {
        "name": "D",
        "height": 2,
        "childrens": []
      },
      {
        "name": "E",
        "height": 2,
        "childrens": []
      },
      {
        "name": "F",
        "height": 2,
        "childrens": []
      }
    ]
  }
]

3.b -- get childrens of B

[
  {
    "name": "D",
    "height": 2,
    "childrens": []
  },
  {
    "name": "E",
    "height": 2,
    "childrens": []
  },
  {
    "name": "F",
    "height": 2,
    "childrens": []
  }
]

3.c 

Negative case try to fetch the node which not exist in the tree -- observe 404 error

http://localhost:8080/node/root

C -- change the parent of given node -- http://localhost:8080/node/changeParent/<node name>/<new parent name>

The api design is not accurate -- just for the demo purpose I implemented using GET method
that way we don't need to relay on rest tools, using browser we will be to demonstrate the 
functionality. 

1. 
http://localhost:8080/node/changeParent/D/A

Observe now the node D is children of A, removed from B.

[
  {
    "name": "root",
    "height": 0,
    "childrens": [
      {
        "name": "A",
        "height": 1,
        "childrens": [
          {
            "name": "C",
            "height": 2,
            "childrens": []
          },
          {
            "name": "D",
            "height": 2,
            "childrens": []
          }
        ]
      },
      {
        "name": "B",
        "height": 1,
        "childrens": [
          {
            "name": "E",
            "height": 2,
            "childrens": []
          },
          {
            "name": "F",
            "height": 2,
            "childrens": []
          }
        ]
      }
    ]
  }
]

2. try to change the parent which is not exist or try to assign the parent for the non existed node
observe server error with proper message.

3. Try to assign the it's own child as the parent -- observe the error.

4. Try to assign the parent as the same current parent (i.e no change in parent and child ) observe nothing got changed.
