#ifndef _SYMBOL_HPP_
#define _SYMBOL_HPP_

#include <fstream>
#include <iostream>
#include <map>
#include <string>
#include <vector>

#include "key.hpp"

class Symbol {
public:
	Symbol(const std::string&);
	void decrypt(const std::string&);
	std::map<Key,Key> M;
	std::vector<Key> T;
};

#endif