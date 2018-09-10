#include <cstdlib>
#include <fstream>
#include <iostream>
#include <string>

//#include "timer.hpp"
#include "key.hpp"
#include "brute.hpp"

std::string me;
std::string encrypted;
std::string table_filename;
bool verbose = false;


Brute::Brute(const std::string& filename) {
	T.resize(N);
	std::string buffer;
	std::fstream input(filename.c_str(), std::ios::in);
    for (int i = 0; i < N; i++) {
     	std::getline(input, buffer);
	    T[i].set_string(buffer);
    }
    input.close();

}

void Brute::decrypt(const std::string& encrypted) {
	// your code here
	//int count = 0;
	Key original(encrypted);
	std::string str = "";
	for(int i = 0; i < C; i++) {
		str += "a";
	}
	str += '\0';
	Key strKey(str);
	std::string strAdd = "";
	for(int i = 0; i < C-1; i++) {
		strAdd += "a";
	}
	strAdd += "b";
	Key adding(strAdd);
	Key ssum;
	for(int i = 0; i < int(1 << N); i++) {
		strKey += strAdd;
		//std::cout << str << std::endl;
		ssum = strKey.subset_sum(T,verbose);
		//ssum.showAlphabets();
		if(original == ssum) {
			std::cout  <<strKey.showAlphabets() << std::endl;
		}
		//count = 0;
		//str = "";
	}
	//std::cout << int(1 << N) << std::endl;
	//Key passkey((std::string(argv[1])));
	
}



void usage(const std::string& error_msg="") {
	if (!error_msg.empty()) std::cout << "ERROR: " << error_msg << '\n';
	std::cout << me << ": Brute force cracking of Subset-sum password with " 
		<< B << " bits precision\n"
	    << "USAGE: " << me << " <encrypted> <table file> [options]\n"
		<< "\nArguments:\n"
		<< " <encrypted>:   encrypted password to crack\n"
		<< " <table file>:  name of file containing the table to use\n"
		<< "\nOptions:\n"
		<< " -h|--help:     print this message\n"
		<< " -v|--verbose:  select verbose mode\n\n";
	exit(0);
}

void initialize(int argc, char* argv[]) {
	me = argv[0];
	if (argc < 3) usage("Missing arguments");
	encrypted = argv[1];
	table_filename = argv[2];
	for (int i=3; i<argc; ++i) {
		std::string arg = argv[i];
		if (arg == "-h" || arg == "--help") usage();
		else if (arg == "-v" || arg == "--verbose") verbose = true;
		else usage("Unrecognized argument: " + arg);
	}
}


int main(int argc, char *argv[]){
//	CPU_timer time;
//	time.tic();
	initialize(argc, argv);
	// your code here
	Brute b(table_filename);
	b.decrypt(encrypted);
//	time.toc();
//	std::cout << "time :" <<time.elapsed() << "milliseconds" <<std::endl;
	return 0;
}
