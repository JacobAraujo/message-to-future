import React, { useEffect, useState } from 'react';
import Header from '../components/Header';
import Sidebar from '../components/Sidebar';
import MessageList from '../components/MessageList';
import { getLimit } from '../services/api';

function MessageManagementPage() {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);
  const [limit, setLimit] = useState();

  const toggleSidebar = () => {
    setIsSidebarOpen(!isSidebarOpen);
  };

  const fetchLimit = async () => {
    try {
      const token = localStorage.getItem('token');
      const data = await getLimit(token);
      setLimit(data);
    } catch (err) {
      console.error('Erro ao buscar limite de mensagens:', err);
    }
  };

  useEffect(() => {
    fetchLimit();
  }, []);

  return (
    <div className="flex min-h-screen bg-gray-50">
      <Sidebar isOpen={isSidebarOpen} toggleSidebar={toggleSidebar} />
      <div className={`flex-1 flex flex-col transition-all duration-300 ${isSidebarOpen ? 'ml-56' : 'ml-0'}`}>
        <Header />
        <main className="flex-1 p-6">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-2xl font-semibold mb-4 text-gray-700">Gerenciar Mensagens</h2>
            <span className="text-sm font-medium text-gray-600 bg-blue-100 px-3 py-1 rounded-lg shadow-md">
                Mensagens disponíveis: {limit}
            </span>
          </div>
          <MessageList onDeleteMessage={fetchLimit} />
        </main>
      </div>
    </div>
  );
}

export default MessageManagementPage;
