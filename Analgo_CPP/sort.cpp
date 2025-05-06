#include <iostream>
#include <vector>
#include <queue>       // for heap (priority_queue)
#include <algorithm>   // for copy
#include <fstream>
#include <sstream>
#include <chrono>
#include <iomanip>     // for setprecision

std::vector<int> read_jumlah_pembelian(const std::string& filename) {
    std::ifstream file(filename);
    std::vector<int> values;
    std::string line;
    int column_index = -1;

    if (!file.is_open()) {
        std::cerr << "Failed to open file.\n";
        return values;
    }

    // Read header
    if (std::getline(file, line)) {
        std::stringstream ss(line);
        std::string col;
        int index = 0;
        while (std::getline(ss, col, ',')) {
            if (col == "Jumlah Pembelian") {
                column_index = index;
                break;
            }
            ++index;
        }
    }

    if (column_index == -1) {
        std::cerr << "Column 'Jumlah Pembelian' not found.\n";
        return values;
    }

    // Read data
    while (std::getline(file, line)) {
        std::stringstream ss(line);
        std::string cell;
        int col = 0;
        while (std::getline(ss, cell, ',')) {
            if (col == column_index) {
                try {
                    values.push_back(std::stoi(cell));
                } catch (...) {}
                break;
            }
            ++col;
        }
    }

    return values;
}

std::vector<int> heap_sort(std::vector<int> arr) {
    std::priority_queue<int, std::vector<int>, std::greater<int>> min_heap(arr.begin(), arr.end());
    std::vector<int> sorted;
    while (!min_heap.empty()) {
        sorted.push_back(min_heap.top());
        min_heap.pop();
    }
    return sorted;
}

void counting_sort(std::vector<int>& arr, int exp) {
    int n = arr.size();
    std::vector<int> output(n);
    int count[10] = {0};

    for (int i = 0; i < n; ++i)
        count[(arr[i] / exp) % 10]++;

    for (int i = 1; i < 10; ++i)
        count[i] += count[i - 1];

    for (int i = n - 1; i >= 0; --i) {
        int index = (arr[i] / exp) % 10;
        output[count[index] - 1] = arr[i];
        count[index]--;
    }

    for (int i = 0; i < n; ++i)
        arr[i] = output[i];
}

void radix_sort(std::vector<int>& arr) {
    if (arr.empty()) return;

    int max_val = *std::max_element(arr.begin(), arr.end());
    for (int exp = 1; max_val / exp > 0; exp *= 10)
        counting_sort(arr, exp);
}

int main() {
    using namespace std::chrono;
    std::vector<int> data = read_jumlah_pembelian("synthetic.csv");

    if (data.empty()) {
        std::cerr << "No data to sort.\n";
        return 1;
    }

    // Heap Sort
    auto heap_data = data;
    auto start = high_resolution_clock::now();
    auto heap_sorted = heap_sort(heap_data);
    auto end = high_resolution_clock::now();
    double heap_time = duration<double>(end - start).count();

    // Radix Sort
    auto radix_data = data;
    start = high_resolution_clock::now();
    radix_sort(radix_data);
    end = high_resolution_clock::now();
    double radix_time = duration<double>(end - start).count();

    // Print sorted arrays
    std::cout << "Heap Sorted Data:\n";
    for (int val : heap_sorted)
        std::cout << val << " ";
    std::cout << "\n";

    std::cout << "Radix Sorted Data:\n";
    for (int val : radix_data)
        std::cout << val << " ";
    std::cout << "\n";

    // Timing info
    std::cout << std::fixed << std::setprecision(6);
    std::cout << "Heap Sort Time: " << heap_time << " seconds\n";
    std::cout << "Radix Sort Time: " << radix_time << " seconds\n";

    return 0;
}