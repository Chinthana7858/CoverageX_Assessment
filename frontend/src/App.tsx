import { useEffect, useState } from "react";
import axios from "axios";
import TaskForm from "./components/TaskForm";
import TaskCard from "./components/TaskCard";
import { API_BASE } from "./services/api_endpoints";
import { Task } from "./types/Task";
import { COLORS } from "./const/colors";

function App() {
  const [tasks, setTasks] = useState<Task[]>([]);

  const fetchTasks = async () => {
  const res = await axios.get(`${API_BASE}/tasks`);
    setTasks(res.data);
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const addTask = async (title: string, description: string) => {
    await axios.post(`${API_BASE}/tasks`, { title, description });
    fetchTasks();
  };

  const markDone = async (id: number) => {
    await axios.patch(`${API_BASE}/tasks/${id}/done`);
    fetchTasks();
  };

  return (
    <div className={`flex flex-col md:flex-row min-h-screen ${COLORS.background} p-6 md:p-12 w-screen`}>
    <div className={`w-full md:w-1/2 md:pr-8 border-b md:border-b-0 md:border-r ${COLORS.border} pb-6 md:pb-0`}>
      <h2 className={`text-2xl font-bold mb-6 ${COLORS.primary}`}>Add a New Task</h2>
      <TaskForm onAdd={addTask} />
    </div>
  
    <div className="w-full md:w-1/2 md:pl-8 pt-6 md:pt-0">
      <h2 className={`text-2xl font-bold mb-6 ${COLORS.primary}`}>To Do List</h2>
      {tasks.length === 0 ? (
        <p className={`${COLORS.lightText} italic`}>No tasks yet. Add some!</p>
      ) : (
        tasks.map((task) => (
          <TaskCard key={task.id} {...task} onDone={markDone} />
        ))
      )}
    </div>
  </div>
  
  );
}

export default App;
