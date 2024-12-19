<template>
    <h1>Items</h1>
    <div>
        <form @submit.prevent="addItem">
            <input type="text" v-model="newItemName" placeholder="Enter item name" />
            <button type="submit">Add Item</button>
        </form>
    </div>
    <div>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in items" :key="item.id">
                    <td>{{ item.id }}</td>
                    <td>{{ item.name }}</td>
                    <td><button @click="openModal(item)">Edit</button></td>
                    <td><button @click="deleteItem(item.id)">Delete</button></td>
                </tr>
            </tbody>
        </table>
    </div>

    <div v-if="isModalOpen" class="modal">
        <div class="modal-content">
            <h2>Edit Item</h2>
            <div>
                update new item name
                <input type="text" id="updateName" v-model="newName" placeholder="Enter new name" />
                <div class="modal-buttons">
                    <button @click="updateItem">Update</button>
                    <button @click="closeModal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Swal from 'sweetalert2';

export default {
    name: "ItemCrud",
    data() {
        return {
            items: [],       // 畫面上用項目
            newItemName: "", // 建立時的名稱
            isModalOpen: false, // 編輯modal是否開啟flag
            selectedItem: null, // 取得當前選中的item項目(for update, delete)
            newName: "", // update item時的新名稱
        };
    },

    mounted() {
        this.fetchItems(); // 啟動時先執行 fetchItem() 取得資料
    },

    methods: {
        fetchItems() {
            fetch("http://localhost:8080/items")
                .then((response) => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
                .then((data) => {
                    this.items = data.items;
                })
                .catch((error) => {
                    console.error("Error fetching items:", error);
                });
        },
        addItem() {
            fetch("http://localhost:8080/items", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ name: this.newItemName }),
            })
                .then(async response => {
                    if (!response.ok) {
                        const errorData = await response.json();
                        const error = new Error();
                        error.status = response.status;
                        error.message = errorData.message;
                        throw error;

                    } else {
                        return this.newItemName;
                    }

                })
                .then(newItem => {
                    this.items.push(newItem);
                    this.newItemName = "";
                    this.fetchItems();
                })
                .catch(error => {
                    console.log(error.message);
                    console.log(error.status);
                    Swal.fire({
                        icon: "error",
                        title: "Add Item Failed",
                        text: error.message,
                    });
                });
        },
        deleteItem(id) {
            fetch(`http://localhost:8080/items/${id}`, {
                method: "DELETE"
            })
                .then(async response => {
                    if (!response.ok) {
                        const errorData = await response.json();
                        const error = new Error();
                        error.status = response.status;
                        error.message = errorData.message;
                        throw error;

                    } else {
                        this.items = this.items.filter((item) => item.id !== id);
                    }
                })
                .catch(error => {
                    console.log(error.message);
                    console.log(error.status);
                    Swal.fire({
                        icon: "info",
                        title: "Item Delete Failed",
                        text: error.message,
                    }).then((result) => {
                        if (result.isConfirmed) {
                            console.log("OK");
                            // 按下 sweetalert 的 OK 後重讀 item list
                            this.fetchItems();
                        }
                    });
                });
        },


        openModal(item) {
            this.selectedItem = item; // 設置當前選中的item項目
            this.newName = item.name; // 初始化 modal 中的item名稱為當前選中的項目的名稱
            this.isModalOpen = true; // 打開 modal
        },
        closeModal() {
            this.selectedItem = null; // 重置選中的item項目
            this.newName = ""; // 重置名稱輸入框
            this.isModalOpen = false; // 關閉 modal
        },
        updateItem() {
            fetch("http://localhost:8080/items", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ id: this.selectedItem.id, name: this.newName }),
            })
                .then(async response => {
                    if (!response.ok) {
                        const errorData = await response.json();
                        const error = new Error();
                        error.status = response.status;
                        error.message = errorData.message;
                        throw error;
                    }
                    // return response.json();

                })
                .then(() => {
                    this.fetchItems(); // 更新成功後重新獲取列表
                    this.closeModal(); // 關閉模態框
                })
                .catch((error) => {
                    console.log("update error : " + error.status);
                    Swal.fire({
                        icon: "error",
                        title: "Update Item Failed",
                        text: error.message,
                    });
                    this.newName = "";
                });
        },
    },
};
</script>

<style scoped>
ul {
    list-style-type: none;
    padding: 0;
}

li {
    margin: 5px 0;
}

button {
    margin-left: 10px;
}

form {
    margin-top: 20px;
}

table {
    margin-top: 10px;
    margin-left: auto;
    margin-right: auto;
}

table,
th,
td {
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 5px;
    padding-bottom: 5px;
    border: 1px solid black;
    border-collapse: collapse;
}

.modal {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
}

.modal-content {
    background: white;
    padding: 20px;
    border-radius: 8px;
    width: 300px;
    text-align: center;
}

.modal-buttons button {
    margin: 10px;
}

#updateName {
    margin: 5px;
}
</style>