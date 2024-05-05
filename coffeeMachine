#include <iostream>

class CoffeeType {
	int beans;
	int water;
	int price;
	std::string name;
public:
	CoffeeType(int beans, int water, int price, std::string name) {
		this->beans = beans;
		this->water = water;
		this->price = price;
		this->name = name;
	}
	int getBeans() { return beans; }
	int getWater() { return water; }
	int getPrice() { return price; }
	std::string getName() { return name; }
};

class CoffeeMachine {

	int beans;
	int water;
	int wallet;

	int check_coffee(CoffeeType coffee) {
		if (beans < coffee.getBeans()) return 1;
		if (water < coffee.getWater()) return 2;
		return 0;
	}

	void remove_price(CoffeeType coffee) {
		beans -= coffee.getBeans();
		water -= coffee.getWater();
		wallet += coffee.getPrice();
	}

	void show_goods() {
		std::cout << "Machine has: " << beans << " beans" << std::endl;
		std::cout << "Machine has: " << water << " water" << std::endl;
	}

	void show_wallet() {
		std::cout << "Wallet: $" << wallet << std::endl;
	}

	void delimeter() {
		std::cout << "-----------------------------" << std::endl;
	}

public:

	CoffeeMachine() {
		beans = 100;
		water = 100;
		wallet = 0;
	}

	void make_coffee(CoffeeType coffee) {
		int check = check_coffee(coffee);
		if (!check) {
			std::cout << "Making " << coffee.getName() << " coffee..." << std::endl;
			remove_price(coffee);
			std::cout << "You spent $" << coffee.getPrice() << std::endl;
		}
		else if (check == 1) {
			std::cout << "No beans." << std::endl;
		}
		else {
			std::cout << "No water." << std::endl;
		}
	}
	void print_goods() {
		delimeter();
		show_goods();
		delimeter();
		show_wallet();
	}
};

int main() {
	CoffeeMachine machine;

	CoffeeType latte(20, 10, 5, "latte");
	CoffeeType cappuccino(10, 50, 4, "cappuccino");
	CoffeeType americano(50, 10, 2, "americano");

	machine.make_coffee(cappuccino);
	machine.make_coffee(latte);
	machine.make_coffee(americano);
	machine.print_goods();
}
