# Simple-blogging-backend.
Simple Spring/REST backend for a blogging site.

Java exercise using Sprint with RESTful interface.

## Getting Started

Clone this repository and run as standalone Java application.

### Prerequisites

You need Java and Web browser to test this.

### Installing

Just clone this repository from: https://github.com/jmhjuha/

## Starting application

java -jar myapp-0.1.0.jar

## REST interface

- HTTP POST /blogs
  - adding new blogpost.
- HTTP GET /blogs/
  - fetches all blogposts
- HTTP GET /blogs/1/comments/
  - fetches all comments from blogpost with id 1
- HTTP POST /blogs/2/comments
  - adding a comment to blogpost 2
- HTTP GET /blogs/1/comments/3
  - fetches comment with id 3 from blogpost with id 1
  