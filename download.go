package main

import (
	//"errors"
	"fmt"
	"io"
	//"log"
	"net/http"
	"os"
	"time"
)

func main() {
	if len(os.Args) == 2 {
		
    fileName := "file-" + time.Now().Format("2006-01-02_15-04-05")
		
    URL := os.Args[1]
		
    err := downloadFile(URL, fileName)
		
		if err != nil {
			//log.Fatal(err)
      fmt.Printf(err)
		}
		//fmt.Printf("File %s downloaded in current working directory", fileName)

		//fmt.Println("YYYY-MM-DD hh:mm:ss : ", currentTime.Format("2006-01-02_15-04-05"))
	}
}

func downloadFile(URL, fileName string) error {
	//Get the response bytes from the url
	response, err := http.Get(URL)
	if err != nil {
		return err
	}
	defer response.Body.Close()

	if response.StatusCode != 200 {
		//return errors.New("Received non 200 response code")
	}
	//Create a empty file
	file, err := os.Create(fileName)
	if err != nil {
		return err
	}
	defer file.Close()

	//Write the bytes to the file
	_, err = io.Copy(file, response.Body)
	if err != nil {
		return err
	}

	return nil
}