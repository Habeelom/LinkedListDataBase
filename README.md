# Student LinkedList DataBase
A Java-based student record management system that implements a double-ended doubly linked list for efficient record sorting and retrieval. The system provides a simple interface for managing student data with multiple sorting capabilities.

## Features
- Add and delete student records
- Search for students by ID
- List students by multiple criteria (ascending/descending):
  - Student ID
  - First Name
  - Last Name
- Duplicate ID prevention
- File-based data persistence
The system is built using several interconnected classes:

### Core Components
1. **COSC311Driver**: Main interface providing a menu-driven system for user interaction
2. **DataBase**: Central management class for student records and operations
3. **DataBaseRecord**: Data structure for individual student records
4. **IndexArray**: Implementation of a double-ended doubly linked list
5. **IndexRecord**: Class managing record keys and locations
6. **Node**: Building block for the linked list structure

### Key Design Features
- Implements a double-ended doubly linked list for efficient bi-directional traversal
- Maintains separate indexes for ID, first name, and last name
- Case-insensitive string comparisons
- Automatic data loading from external files

## Data Structure Details
The system utilizes multiple data structures working in concert:
- **Array Storage**: Base storage for student records
- **Linked Lists**: Three separate linked lists for different sorting criteria
- **Index Records**: Mapping between sort keys and array locations

## How to Use
1. Compile all Java files in the package
2. Run COSC311Driver class
3. Use the menu interface to:
   - Add new students (Option 1)
   - Delete existing records (Option 2)
   - Find students by ID (Option 3)
   - List students in various orders (Options 4-9)

## Implementation Notes
- Records are automatically sorted during insertion
- Duplicate IDs are prevented with appropriate user notification
- File data is loaded and validated during initialization
- All string comparisons are case-insensitive
- The system supports up to 100 student records

## Optimizations
- Efficient record insertion maintaining sorted order
- O(log n) search complexity for ID lookups
- Memory-efficient index structure
- Bidirectional traversal capability

## Technical Requirements
- Java Runtime Environment (JRE)
- Text file containing initial student data
