#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <string>
#include <cmath>
#include <algorithm>
#include <chrono>
#include <iomanip>

using namespace std;
using namespace std::chrono;

// Jump Search
int jump_search(const vector<string>& arr, const string& x) {
    int n = arr.size();
    int step = sqrt(n);
    int prev = 0;

    while (prev < n && arr[min(step, n) - 1] < x) {
        prev = step;
        step += sqrt(n);
        if (prev >= n) return -1;
    }

    while (prev < n && arr[prev] < x) {
        prev++;
        if (prev == min(step, n)) return -1;
    }

    if (arr[prev] == x) return prev;
    return -1;
}

// Interpolation Search (approximate using string length)
int interpolation_search(const vector<string>& arr, const string& x) {
    int low = 0, high = arr.size() - 1;

    while (low <= high && x >= arr[low] && x <= arr[high]) {
        // Estimate mid using normal binary mid since actual interpolation is not possible
        int pos = low + (high - low) / 2;

        if (arr[pos] == x) return pos;
        else if (arr[pos] < x) low = pos + 1;
        else high = pos - 1;
    }
    return -1;
}


// Read CSV and extract "Nama Pelanggan" column
vector<string> read_nama_pelanggan(const string& filename) {
    vector<string> names;
    ifstream file(filename);
    string line, cell;

    if (!file.is_open()) {
        cerr << "Failed to open file." << endl;
        return names;
    }

    getline(file, line);  // header
    int column_index = -1;

    // Identify the index of "Nama Pelanggan"
    stringstream header_stream(line);
    vector<string> headers;
    while (getline(header_stream, cell, ',')) {
        headers.push_back(cell);
    }

    for (size_t i = 0; i < headers.size(); ++i) {
        if (headers[i] == "Nama Pelanggan") {
            column_index = i;
            break;
        }
    }

    if (column_index == -1) {
        cerr << "\"Nama Pelanggan\" column not found." << endl;
        return names;
    }

    while (getline(file, line)) {
        stringstream line_stream(line);
        int col = 0;
        while (getline(line_stream, cell, ',')) {
            if (col == column_index) {
                names.push_back(cell);
                break;
            }
            col++;
        }
    }

    file.close();
    return names;
}

int main() {
    string filename = "synthetic.csv";
    vector<string> customer_ids = read_nama_pelanggan("synthetic.csv");

    if (customer_ids.empty()) {
        cerr << "No data found in 'Nama Pelanggan' column." << endl;
        return 1;
    }
    
    // Explicitly sort the names before search
    sort(customer_ids.begin(), customer_ids.end());
    cout << "Data has been sorted by 'Nama Pelanggan' before search.\n";    

    string customer_id_to_search = "Aditya Bayhaqie";

    // Jump Search
    auto start = high_resolution_clock::now();
    int index_jump = jump_search(customer_ids, customer_id_to_search);
    auto end = high_resolution_clock::now();
    auto jump_time = duration<double>(end - start).count();

    // Interpolation Search (simulated for strings using mid-point)
    start = high_resolution_clock::now();
    int index_interp = interpolation_search(customer_ids, customer_id_to_search);
    end = high_resolution_clock::now();
    auto interp_time = duration<double>(end - start).count();

    // Output results
    cout << "Searching for: " << customer_id_to_search << endl;

    cout << "Jump Search" << endl;
    cout << "Jump Search: Index = " << index_jump << ", Time = " << fixed << setprecision(30) << jump_time << " seconds" << endl;
    
    cout << "Interpolation Search" << endl;
    cout << "Interpolation Search: Index = " << index_interp << ", Time = " << fixed << setprecision(30) << interp_time << " seconds" << endl;

    return 0;
}