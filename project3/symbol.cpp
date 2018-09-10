#include <cstdlib>
#include <fstream>
#include <iostream>
#include <string>

#include "key.hpp"
#include "symbol.hpp"
//#include "timer.hpp"
#define ALPHABET "abcdefghijklmnopqrstuvwxyz012345"

std::string me;
std::string encrypted;
std::string table_filename;
bool verbose = false;

Symbol::Symbol(const std::string& filename) {
	T.resize(N);
	std::string buffer;
	std::fstream input(filename.c_str(), std::ios::in);
	for (int i = 0; i < N; i++) {
		std::getline(input, buffer);
		T[i].set_string(buffer);
	}
	input.close();
	
	// insert your code here
}
/*
brute :
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
*/
void Symbol::decrypt(const std::string& encrypted){
	// insert your code here
	Key original(encrypted);
	if(C % 2 == 0) {
		std::string str = "";
		for(int i = 0; i < C; i++) {
			str += "a";
		}
		str += '\0';
		Key strKey(str);
		std::string strAdd1 = "";
		for(int i = 0; i < C/2-1; i++) {
			strAdd1 += "a";
		}
		strAdd1 += "b";
		for(int i = C/2; i < C ;i++) {
			strAdd1 += "a";
		}
		strAdd1 += "\0";
		Key adding(strAdd1);
		Key ssum;
		for(int i = 0; i < int(1 << N/2); i++) {
		//std::cout << "first half: " << strKey.showAlphabets() << std::endl;
			ssum = strKey.subset_sum(T,verbose);
		//std::cout << "first half ssum: " << ssum.showAlphabets() << std::endl;
			M.insert(std::make_pair(ssum,strKey));		
			strKey += strAdd1;

		}
		std::string strAdd2 = "";
		std::string str1 = "";
	//std::cout << "after half" << std::endl;
		for(int i =0; i < C; i++) {
			str1 += "a";
		}
		Key strKey1(str1);
		for(int i =0; i < C-1; i++){
			strAdd2 += "a";
		}
		strAdd2 += "b";

		for(int i = 0; i < int(1<<N/2); i++) {
			Key tmp = original;
		//std::cout << "tmp before sub: " << tmp.showAlphabets() << std::endl;
		//std::cout << "strKey1: " <<strKey1.showAlphabets() << std::endl;
		tmp -= strKey1.subset_sum(T,verbose); // orignal - aaaa-> aa55
		//std::cout << "tmp after sub: "<<tmp.showAlphabets() << std::endl;
		std::map<Key,Key>::const_iterator pos = M.find(tmp);
		if (pos == M.end()) {
    		//handle the error
		} else {
			Key value = pos->second;
    		//std::cout <<"M value1 : " << value.showAlphabets() << std::endl;
    		//std::cout << "stdKey1 : " <<strKey1.showAlphabets() << std::endl;
			std::string result = value.showAlphabets().erase(C/2,C);
			result += strKey1.showAlphabets().erase(0,C/2);
			std::cout <<  result << std::endl;
		}
		//std::cout << "original: "<<original.showAlphabets() << std::endl;
		
		//std::cout << "tmp: " << tmp.showAlphabets() << std::endl;
		//std::cout <<"M.first: " << M.find(tmp)->first.showAlphabets() << std::endl;
		strKey1 += strAdd2;
		}
	
		//std::cout << "-= test " << pra.showAlphabets() << std::endl;
		//std::cout << int(1 << N) << std::endl;
		//Key passkey((std::string(argv[1])));
	}
	if(C%2 == 1) {
		//std::cout << "odd" << std::endl;
		std::string str = "";
		for(int i = 0; i < C; i++) {
			str += "a";
		}
		Key strKey(str);
		std::string strAdd1 = "";
		for(int i = 0; i < (C-1)/2-1; i++) {
			strAdd1 += "a";
		}
		strAdd1 += "b";
		for(int i = (C-1)/2; i < C ;i++) {
			strAdd1 += "a";
		}
		Key adding(strAdd1);
		Key ssum;
		for(int i = 0; i < int(1 << (N-C)/2); i++) {
		//std::cout << "first half: " << strKey.showAlphabets() << std::endl;
			ssum = strKey.subset_sum(T,verbose);
		//std::cout << "first half ssum: " << ssum.showAlphabets() << std::endl;
			M.insert(std::make_pair(ssum,strKey));		
			strKey += strAdd1;

		}
		std::string strAdd2 = "";
		std::string str1 = "";
	//std::cout << "after half" << std::endl;
		for(int i =0; i < C; i++) {
			str1 += "a";
		}
		Key strKey1(str1);
		for(int i =0; i < C-1; i++){
			strAdd2 += "a";
		}
		strAdd2 += "b";
		Key adding1(strAdd2);
		for(int i = 0; i < int(1 << (N+C)/2); i++) {
			//std::cout << int(1 <<(N+C)/2) << std::endl;

			Key tmp = original;
		//std::cout << "tmp before sub: " << tmp.showAlphabets() << std::endl;
		//std::cout << "strKey1: " <<strKey1.showAlphabets() << std::endl;
			tmp -= strKey1.subset_sum(T,verbose); // orignal - aaaa-> aa55
			std::string tempstr = "";
			for(int i = 0; i < (C-1)/2-1; i++) {
				tempstr += "a";
			}
			tempstr += ALPHABET[2];
			for(int i =0; i < C-1; i++){
				tempstr += "a";
			}
			Key tmpKey(tempstr);
			//std::cout << strKey1.getOffset() << std::endl;
			tmp += tempstr;
		//std::cout << "tmp after sub: "<<tmp.shows() << std::endl;
			std::map<Key,Key>::const_iterator pos = M.find(tmp);
			if (pos == M.end()) {
    		//handle the error
			} else {
				Key value = pos->second;
    			//std::cout <<"M value1 : " << value.showAlphabets() << std::endl;
    		//	std::cout << "stdKey1 : " <<strKey1.showAlphabets() << std::endl;
				std::string result = value.showAlphabets().erase((C-1)/2,C);
				result += strKey1.showAlphabets().erase(0,(C-1)/2);
				std::cout <<  result << std::endl;
			}
		//std::cout << "original: "<<original.showAlphabets() << std::endl;
		
		//std::cout << "tmp: " << tmp.showAlphabets() << std::endl;
		//std::cout <<"M.first: " << M.find(tmp)->first.showAlphabets() << std::endl;
			strKey1 += adding1;
		}
	
		//std::cout << "-= test " << pra.showAlphabets() << std::endl;
		//std::cout << int(1 << N) << std::endl;
		//Key passkey((std::string(argv[1])));
	}
}

void usage(const std::string& error_msg="") {
	if (!error_msg.empty()) std::cout << "ERROR: " << error_msg << '\n';
	std::cout << me << ": Symbol table-based cracking of Subset-sum password"
	<< " with " << B << " bits precision\n"
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
	//CPU_timer time;
	//time.tic();
	initialize(argc, argv);
	
	// your code here
	Symbol s(table_filename);
	s.decrypt(encrypted);
	//time.toc();
//	std::cout << "time :" <<time.elapsed() << "milliseconds" <<std::endl;
	return 0;
}
